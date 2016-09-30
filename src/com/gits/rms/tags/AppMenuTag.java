
package com.gits.rms.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gits.rms.vo.MenuVO;

public class AppMenuTag extends TagSupport {

    private static final long serialVersionUID = -1592749024311884544L;

    @Override
    public int doEndTag() throws JspException {
        List<MenuVO> l = (List<MenuVO>) pageContext.getRequest().getAttribute("menu");
        JspWriter out = pageContext.getOut();
        try {
            out.println("<script>");
            out.println("var str_jSon= '" + getMenuDataScript(l) + "';");
            out.println(getMenuCodeScript());
            out.println("</script>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (EVAL_PAGE);
    }

    private String getMenuCodeScript() {
        return " YAHOO.util.Event.onDOMReady(function () { "
            + " var jSon = YAHOO.lang.JSON.parse(str_jSon); "
            + " var menuBar = new YAHOO.widget.MenuBar('sample_menubar',{ autosubmenudisplay: true }); "
            + " YAHOO.util.Dom.addClass(menuBar.element, 'yuimenubarnav');  "
            + " menuBar.addItems(jSon); " + " menuBar.render('menu_div'); " + "}); ";
    }

    private String getMenuDataScript(List<MenuVO> l) {
        StringBuffer jSon = new StringBuffer();
        jSon.append("[");
        menuToJSon(l, jSon);
        jSon.append("]");
        return jSon.toString();
    }

    public void menuToJSon(List<MenuVO> l, StringBuffer jSon) {
        for (int i = 0; i < l.size(); i++) {
            MenuVO menu = l.get(i);
            String s1 = menu.getLabel();
            jSon.append("{\"text\":");
            jSon.append("\"" + s1 + "\"");

            if ((menu.getUrl() != null) && !menu.getUrl().equals("")) {
                jSon.append(",\"url\":");
                jSon.append("\"" + menu.getUrl() + "\"");
            }

            if ((menu.getSubmenus() != null) && (menu.getSubmenus().size() > 0)) {
                jSon.append(",\"submenu\":{\"id\":\"" + menu.getId() + "\", \"itemdata\":[");
                menuToJSon(menu.getSubmenus(), jSon);
                jSon.append("]}");
            }

            jSon.append("}");
            if (i != (l.size() - 1)) {
                jSon.append(",");
            }
        }
    }
}