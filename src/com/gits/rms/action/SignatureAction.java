
package com.gits.rms.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.SignatureVO;

public class SignatureAction extends ActionSupport {
    private static final long serialVersionUID = 4864687019760193292L;
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO signature;
    private SignatureVO signatureObj;

    // To get List of Signature
    @SkipValidation
    public String getAllSignature() {
        signatureList = signatureService.getAllSignatureForLoginEmp();
        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
            setPreferrdSignatureValue(it.next());
        }
        return SUCCESS;
    }

    // To View the Signature Search Form
    @SkipValidation
    public String signatureSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String signatureSearchResult() {
        signatureList = signatureService.getAllSignatureForLoginEmp();
        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
            setPreferrdSignatureValue(it.next());
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Signature it shows blank Form to enter New Data
    @SkipValidation
    public String setUpSignature() {
        if ((signature != null) && (signature.getHcmoSignatureId() != null)) {
            signature = signatureService.getSignature(signature.getHcmoSignatureId());
        }
        return SUCCESS;
    }

    private void setUpSignature(Integer hcmoSignatureId) {
        signature = signatureService.getSignatureEmployee(signature.getHcmoSignatureId());
    }

    // To get Particular Signature Data
    @SkipValidation
    public String signatureView() {
        if ((signature != null) && (signature.getHcmoSignatureId() != null)) {
            signature = signatureService.getSignature(signature.getHcmoSignatureId());
            setPreferrdSignatureValue(signature);
        }
        return SUCCESS;
    }

    // To insert new Signature detail or Edit Particular Signature Detail
    public String insertOrUpdateSignature() {
        try {
            if (signature.getHcmoSignatureId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                signature.setEmpIdObj(oEmp);
                signatureList = signatureService.getEmployeeAllSignature(signature.getEmpIdObj().getEmployeeId());
                if (signature.isPreSignature() == false) {
                    if (signatureList.isEmpty() == true) {
                        signature.setCreated(DateUtils.getCurrentDateTime());
                        signature.setCreatedBy(oEmp);
                        signature.setUpdatedBy(oEmp);
                        signature.setIsActive(1);
                        signature.setPreSignature(true);
                        signature.setEmpIdObj(oEmp);
                        signatureService.insertSignature(signature);
                        session.put("HELP_INFORMATION_MESSAGE", getText("Your first Signature will be made as Preferred Signature Automatically"));
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    } else if (signatureList.isEmpty() == false) {
                        signature.setCreated(DateUtils.getCurrentDateTime());
                        signature.setCreatedBy(oEmp);
                        signature.setUpdatedBy(oEmp);
                        signature.setIsActive(1);
                        signature.setEmpIdObj(oEmp);
                        signatureService.insertSignature(signature);
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    }
                } else if (signature.isPreSignature() == true) {
                    SignatureVO newSig = null;
                    if (signatureList.isEmpty() == false) {
                        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                            newSig = it.next();
                            if (newSig.isPreSignature() == true) {
                                newSig.setPreSignature(false);
                                newSig.setUpdatedBy(oEmp);
                                signatureService.updateSignature(newSig);
                            }
                        }
                        signature.setCreated(DateUtils.getCurrentDateTime());
                        signature.setCreatedBy(oEmp);
                        signature.setUpdatedBy(oEmp);
                        signature.setIsActive(1);
                        signature.setEmpIdObj(oEmp);
                        signatureService.insertSignature(signature);
                        session.put("HELP_INFORMATION_MESSAGE", getText("Your Previous Preferred Signature has been made as Non- Preferred Signature"));
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    } else if (signatureList.isEmpty() == true) {
                        signature.setCreated(DateUtils.getCurrentDateTime());
                        signature.setCreatedBy(oEmp);
                        signature.setUpdatedBy(oEmp);
                        signature.setIsActive(1);
                        signature.setEmpIdObj(oEmp);
                        signatureService.insertSignature(signature);
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    }
                }

            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                signature.setUpdatedBy(oEmp);
                signature.setEmpIdObj(oEmp);
                if (signature.isPreSignature() == true) {
                    signatureList = signatureService.getAllSignatureForEmp(signature);
                    if (signatureList.isEmpty() == false) {
                        signatureService.updateSignature(signature);
                        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                            signature = it.next();
                            if (signature.isPreSignature() == true) {
                                signature.setPreSignature(false);
                                signatureService.updateSignature(signature);
                            }
                        }
                        signatureService.updateSignature(signature);
                        addActionMessage(getText("Updated Successfully"));
                        return SUCCESS;
                    } else if (signatureList.isEmpty() == true) {
                        signatureService.updateSignature(signature);
                        addActionMessage(getText("Updated Successfully"));
                        return SUCCESS;
                    }
                    signatureService.updateSignature(signature);
                    addActionMessage(getText("Updated Successfully"));
                    return SUCCESS;
                } else if (signature.isPreSignature() == false) {
                    signatureList = signatureService.getAllSignatureForEmp(signature);
                    if (signatureList.isEmpty() == false) {
                        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                            signatureObj = it.next();
                            if (signatureObj.isPreSignature() == true) {
                                signatureService.updateSignature(signature);
                                addActionMessage(getText("Updated Successfully"));
                                return SUCCESS;
                            }
                        }
                        setUpSignature(signature.getHcmoSignatureId());
                        addActionError(getText("You cant update the only Preferred Signature to Non-Preferred Signature"));
                        signature.setPreSignature(true);
                        return INPUT;
                    } else if (signatureList.isEmpty() == true) {
                        setUpSignature(signature.getHcmoSignatureId());
                        addActionError(getText("You cant update the only Preferred Signature to Non-Preferred Signature"));
                        return INPUT;
                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    // To delete Particular Signature Detail
    @SkipValidation
    public String deleteSignature() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        signature.setUpdatedBy(oEmp);
        signature.setEmpIdObj(oEmp);

        setUpSignature(signature.getHcmoSignatureId());
        signatureList = signatureService.getAllSignatureForEmp(signature);
        if (signatureList.isEmpty() == true) {
            signature = signatureService.getSignatureEmployee(signature.getHcmoSignatureId());
            signatureService.deleteSignature(signature);
            addActionError(getText("You have deleted Only Preferred Signature of ")
                + signature.getEmpIdObj().getEmpFirstName() + getText(""));
            return ERROR;
        } else if (signatureList.isEmpty() == false) {
            if (signature.isPreSignature() == true) {
                addActionError("Please Change Some other Signature of this Employee as Preferred to delete this Signature ");
                return ERROR;
            } else if (signature.isPreSignature() == false) {
                signature = signatureService.getSignatureEmployee(signature.getHcmoSignatureId());
                signatureService.deleteSignature(signature);
                addActionMessage("Deleted Successfully");
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String makePreferredSignature() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        signature.setUpdatedBy(oEmp);

        setUpSignature(signature.getHcmoSignatureId());
        signature.setPreSignature(true);
        signatureService.updateSignature(signature);
        signatureList = signatureService.getAllSignatureForEmp(signature);

        for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
            signature = it.next();

            if (signature.isPreSignature() == true) {
                signature.setPreSignature(false);
                signature.setUpdatedBy(oEmp);
                signatureService.updateSignature(signature);
            }
        }
        addActionMessage("Your Signature has been made as Preferred Signature");
        return SUCCESS;
    }

    // Convert Int to String for PreferredSignature Field
    public void setPreferrdSignatureValue(SignatureVO signature) {
        signature.setPreSignatureValue(signature.isPreSignature() == true ? getText("label.signature.length.value.preferred")
            : getText("label.signature.length.value.nonPreferred"));
    }

    public SignatureVO getSignature() {
        return signature;
    }

    public void setSignature(SignatureVO signature) {
        this.signature = signature;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setSignatureObj(SignatureVO signatureObj) {
        this.signatureObj = signatureObj;
    }

    public SignatureVO getSignatureObj() {
        return signatureObj;
    }
}
