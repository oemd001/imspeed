import React, { useRef, useEffect, useState } from "react";
import styles from "./MeetingHitRateChart.module.scss";
import { numeral } from "utils";
import { ColumnChart } from "brio-ui-components";

const MeetingHitRateChart = ({
  hitRatiosByMeetingType,
  hitRatiosByPreDeal,
}) => {
  const chartRef = useRef(null);
  const [tooltipVisible, setTooltipVisible] = useState(false);
  const [tooltipContent, setTooltipContent] = useState("");

  useEffect(() => {
    if (chartRef.current && chartRef.current.chart) {
      const chart = chartRef.current.chart;
      chart.legend.allItems.forEach((item) => {
        const legendItemElement = item.legendItem.element;

        legendItemElement.addEventListener("mouseover", function () {
          setTooltipContent(item.name);
          setTooltipVisible(true);
        });

        legendItemElement.addEventListener("mouseout", function () {
          setTooltipVisible(false);
        });
      });
    }

    // Cleanup event listeners on unmount
    return () => {
      if (chartRef.current && chartRef.current.chart) {
        const chart = chartRef.current.chart;
        chart.legend.allItems.forEach((item) => {
          const legendItemElement = item.legendItem.element;

          legendItemElement.removeEventListener("mouseover", function () {});
          legendItemElement.removeEventListener("mouseout", function () {});
        });
      }
    };
  }, [chartRef]);

  // ...rest of your component logic...

  return (
    <div className={styles.panel}>
      <div className={styles.title}>Meeting Hit Rate</div>
      <ColumnChart
        ref={chartRef}
        xAxisLabels={yValues.map(yFormatter)}
        suppressDataLabels
        series={[{ data: yValues }]}
        yFormatter={yFormatter}
        groupPadding={0.3}
        className={styles.chart}
        shadowHeight={100}
        type={"bar"}
        stacking={"normal"}
        legend={{
          useHTML: true,
          labels: visibleMeetingTypes.map(
            (meetingType) => meetingTypeLabels[meetingType]
          ),
          align: "center",
          verticalAlign: "bottom",
        }}
      />
      {tooltipVisible && (
        <div className={styles.tooltip}>
          {tooltipContent}
        </div>
      )}
    </div>
  );
};

export default MeetingHitRateChart;
