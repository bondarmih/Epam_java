package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajax_002dadd_002dpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\t\n");
      out.write("\t<script type=\"text/javascript\" src=\"/spring-mvc-jquery/resources/js/jquery/jquery-1.4.4.min.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t    var jq = jQuery.noConflict();\n");
      out.write("\t</script>\n");
      out.write("\t\n");
      out.write("\t<title>Spring MVC - jQuery Integration Tutorial</title>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<h3>Spring MVC - jQuery Integration Tutorial</h3>\n");
      out.write("<h4>AJAX version</h4>\n");
      out.write("\n");
      out.write("Demo 1\n");
      out.write("<div style=\"border: 1px solid #ccc; width: 250px;\">\n");
      out.write("\tAdd Two Numbers: <br/>\n");
      out.write("\t<input id=\"inputNumber1\" type=\"text\" size=\"5\"> +\n");
      out.write("\t<input id=\"inputNumber2\" type=\"text\" size=\"5\">\n");
      out.write("\t<input type=\"submit\" value=\"Add\" onclick=\"add()\" /> <br/>\n");
      out.write("\tSum: <span id=\"sum\">(Result will be shown here)</span>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\"> \n");
      out.write("\n");
      out.write("function add() {\n");
      out.write("\tjq(function() {\n");
      out.write("\t\t// Call a URL and pass two arguments\n");
      out.write("\t\t// Also pass a call back function\n");
      out.write("\t\t// See http://api.jquery.com/jQuery.post/\n");
      out.write("\t\t// See http://api.jquery.com/jQuery.ajax/\n");
      out.write("\t\t// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' \n");
      out.write("\t\t// See http://bugs.jquery.com/ticket/7535\n");
      out.write("\t\tjq.post(\"/spring-mvc-jquery/krams/main/ajax/add\",\n");
      out.write("\t\t\t\t\t{ \tinputNumber1:  jq(\"#inputNumber1\").val(),\n");
      out.write("\t\t\t\t  \t\tinputNumber2:  jq(\"#inputNumber2\").val()\n");
      out.write("\t\t\t\t  \t},\n");
      out.write("\t\t\t\t\t\tfunction(data){\n");
      out.write("\t\t\t\t\t\t\t// data contains the result\n");
      out.write("\t\t\t\t\t\t\t// Assign result to the sum id\n");
      out.write("\t\t\t\t\t\t\tjq(\"#sum\").replaceWith('<span id=\"sum\">'+ data + '</span>');\n");
      out.write("                        }\n");
      out.write("\t\t);\n");
      out.write("\t});\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
