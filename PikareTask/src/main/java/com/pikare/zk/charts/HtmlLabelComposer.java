package com.pikare.zk.charts;

import org.zkoss.chart.Charts;
import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;

public class HtmlLabelComposer extends SelectorComposer<Component> {

    @Wire
    Charts chart;
    
    private static CategoryModel model;
    
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        model = new DefaultCategoryModel();
		model.setValue("John", "<a title='great!!' href='http://www.zkoss.org' class='hastip'>Apples</a>", new Integer(5));
		model.setValue("John", "<a title='great!!' href='http://www.zkoss.org' class='hastip'>Orange</a>", new Integer(7));
		model.setValue("Peter", "<a title='great!!' href='http://www.zkoss.org' class='hastip'>Apples</a>", new Integer(5));
		model.setValue("Peter", "<a title='great!!' href='http://www.zkoss.org' class='hastip'>Orange</a>", new Integer(8));
        
        chart.setModel(model);
        chart.getYAxis().setMin(0);
        chart.getYAxis().setTitle("Total fruit consumption");
        
        // enable html usage 
        chart.getXAxis().getLabels().setUseHTML(true);
    }
}
