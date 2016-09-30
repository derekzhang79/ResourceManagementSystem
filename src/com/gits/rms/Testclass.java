package com.gits.rms;

public class Testclass {
	
		private static final long serialVersionUID = 1L;
		
		private String address2;
		
		private Integer hcmoclientregId;
		
		private Integer clientid;
		
		private String firstName;
		
		private String lastName;
		
		private String companyName;
		
		private String emailId;
		
		private String password;
		
		private String phone;
		
		private String address1;

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public Integer getHcmoclientregId() {
			return hcmoclientregId;
		}

		public void setHcmoclientregId(Integer hcmoclientregId) {
			this.hcmoclientregId = hcmoclientregId;
		}

		public Integer getClientid() {
			return clientid;
		}

		public void setClientid(Integer clientid) {
			this.clientid = clientid;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Testclass(String address2, Integer hcmoclientregId, Integer clientid, String firstName, String lastName,
				String companyName, String emailId, String password, String phone, String address1) {
			super();
			this.address2 = address2;
			this.hcmoclientregId = hcmoclientregId;
			this.clientid = clientid;
			this.firstName = firstName;
			this.lastName = lastName;
			this.companyName = companyName;
			this.emailId = emailId;
			this.password = password;
			this.phone = phone;
			this.address1 = address1;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		
		

	}



