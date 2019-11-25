package com.example.charttool;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class RadarMarkerView extends MarkerView {

    TextView txvMarker;
    DecimalFormat format = new DecimalFormat("##");
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        txvMarker = findViewById(R.id.txvMarker);

    }

    @Override
    public MPPointF getOffset() {
        return super.getOffset();
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        txvMarker.setText(String.format("%s %%", format.format(e.getY())));
        super.refreshContent(e, highlight);
    }


}
