package com.pikare.zk.charts;

import org.zkoss.chart.Charts;
import org.zkoss.chart.Lang;
import org.zkoss.chart.Legend;
import org.zkoss.chart.Options;
import org.zkoss.chart.PlotLine;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class LineBasicComposer extends SelectorComposer<Window> {

    @Wire
    Charts chart;

    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        
        chart.setModel(LineBasicData.getCategoryModel());
        
        chart.getTitle().setX(-20);
    
        chart.getSubtitle().setX(-20);
    
        chart.getYAxis().setTitle("Sayi");
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(1);
        plotLine.setColor("#808080");
        chart.getYAxis().addPlotLine(plotLine);

        chart.getTooltip().setValueSuffix("");
        Options options = chart.getOptions();
        if (options == null) options = new Options();

        Lang lang = options.getLang();
        if (lang == null) lang = new Lang();

        lang.setNoData("Gösterilecek veri bulunamadi");
        options.setLang(lang);
        chart.setOptions(options);
        
        Legend legend = chart.getLegend();
        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("middle");
        legend.setBorderWidth(0);
    }
}
