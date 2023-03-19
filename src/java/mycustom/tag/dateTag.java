/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package mycustom.tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ADMIN
 */
public class dateTag extends SimpleTagSupport {

    private Date value;
    private String type;

    public static String displayDate(Date value, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat();
        String strDate = null;
        if (type == null) {
            formatter = new SimpleDateFormat("dd/MM/yyyy");
        } else if (type.equals("dateMonth")) {
            formatter = new SimpleDateFormat("dd/MM");
        } else if (type.equals("EEEE")) {
            formatter = new SimpleDateFormat("EEEE", Locale.getDefault());
        } else if (type.equals("EEE")) {
            formatter = new SimpleDateFormat("EEE", Locale.getDefault());
        } else if (type.equals("MMMM")) {
            formatter = new SimpleDateFormat("dd MMMM yyyy");
        } else if (type.equals("MMM")) {
            formatter = new SimpleDateFormat("dd MMM yyyy");
        } else {
            formatter = new SimpleDateFormat("dd/MM/yyyy");
        }
        strDate = formatter.format(value);
        return (strDate);
    }

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            out.print(displayDate(value, type));
        } catch (java.io.IOException ex) {
            throw new JspException("Error in dateTag tag", ex);
        }
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

}
