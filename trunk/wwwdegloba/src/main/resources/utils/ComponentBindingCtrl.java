package utils;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
 
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
 
import org.richfaces.component.UICalendar;
import org.richfaces.component.UIOutputPanel;
 
@ManagedBean(name = "componentBindingCtrl")
@SessionScoped
public class ComponentBindingCtrl{
  
    private Date testDate = new Date();
    
    public Date getTestDate() {
        return testDate;
    }
 
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
 
    private HtmlForm form;
 
    public HtmlForm getForm() {
        return form;
    }
 
    public void setForm(final HtmlForm form) {
        this.form = form;
    }
 
    public ComponentBindingCtrl() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExpressionFactory expressionFactory = facesContext.getApplication()
                .getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        form = new HtmlForm();        
       
        HtmlPanelGrid panelgrid = new HtmlPanelGrid();
        panelgrid.setColumns(1);
        panelgrid.setBorder(0);
        form.getChildren().add(panelgrid);   
        
        UIOutputPanel panel = new UIOutputPanel();
        panel.setId("panel");
        panelgrid.getChildren().add(panel);
        
        
        UICalendar calendar = (UICalendar) facesContext.getApplication().createComponent(facesContext, "org.richfaces.component.UICalendar", "org.richfaces.renderkit.html.CalendarRenderer");

        
        //UICalendar calendar = new UICalendar();
        calendar.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{componentBindingCtrl.testDate}", Date.class));
        calendar.setEnableManualInput(true); 
        calendar.setRequired(false);
        calendar.setId("cal");       
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setLocale(Locale.getDefault());
        panel.getChildren().add(calendar);         
    }
}
