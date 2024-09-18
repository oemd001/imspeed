import React, { useRef, useEffect } from "react";
import styles from "./MeetingHitRateChart.module.scss";
import { numeral } from "utils";
import { ColumnChart } from "brio-ui-components";
import Highcharts from "highcharts";

const MeetingHitRateChart = ({
  hitRatiosByMeetingType,
  hitRatiosByPreDeal,
}: {
  hitRatiosByMeetingType: PartialRecord<MeetingType, number | undefined>;
  hitRatiosByPreDeal: PartialRecord<MeetingType, number | undefined>;
}): JSX.Element => {
  const chartRef = useRef<Highcharts.Chart>();

  const yFormatter = (y: number | undefined): string =>
    `${numeral(y).format("0.0")}%`;

  const integratedHitRatios = {
    ...hitRatiosByMeetingType,
    "Testing the Waters": hitRatiosByPreDeal["Testing the Waters"],
  };

  const meetingTypes: MeetingType[] = [
    "Testing the Waters",
    "1x1",
    "Small Group",
    "Large Group",
  ];

  const meetingTypeLabels: Record<MeetingType, string> = {
    "Testing the Waters": "Testing the Waters",
    "1x1": "1x1 Meetings",
    "Small Group": "Small Group Roadshows",
    "Large Group": "Large Group Roadshows",
  };

  const visibleMeetingTypes: MeetingType[] = meetingTypes.filter(
    (meetingType) => integratedHitRatios[meetingType] !== undefined
  );

  // Create series for each meeting type
  const series = visibleMeetingTypes.map((meetingType) => ({
    name: meetingTypeLabels[meetingType],
    data: [integratedHitRatios[meetingType]],
  }));

  useEffect(() => {
    if (chartRef.current) {
      const chart = chartRef.current;

      // Remove existing legend item events
      chart.legend.allItems.forEach((item) => {
        Highcharts.removeEvent(item.legendItem.element, 'mouseover');
        Highcharts.removeEvent(item.legendItem.element, 'mouseout');
      });

      // Add hover events to legend items
      chart.legend.allItems.forEach((item) => {
        const legendItemElement = item.legendItem.element;

        Highcharts.addEvent(legendItemElement, "mouseover", function (e: MouseEvent) {
          const tooltip = document.getElementById("legend-tooltip");
          if (tooltip) {
            tooltip.style.display = "block";
            tooltip.style.left = `${e.pageX + 10}px`;
            tooltip.style.top = `${e.pageY + 10}px`;
            tooltip.innerHTML = `Tooltip for ${item.name}`;
          }
        });

        Highcharts.addEvent(legendItemElement, "mouseout", function () {
          const tooltip = document.getElementById("legend-tooltip");
          if (tooltip) {
            tooltip.style.display = "none";
          }
        });
      });
    }
  }, [series]);

  return (
    <div className={styles.panel}>
      <div className={styles.title}>Meeting Hit Rate</div>
      <div className={styles.chartContainer}>
        <ColumnChart
          ref={(chart: Highcharts.Chart) => {
            chartRef.current = chart;
          }}
          xAxisLabels={[""]}
          suppressDataLabels
          series={series}
          yFormatter={yFormatter}
          groupPadding={0.3}
          className={styles.chart}
          shadowHeight={100}
          type={"bar"}
          stacking={"normal"}
          legend={{
            useHTML: true,
            align: "center",
            verticalAlign: "bottom",
          }}
        />
        <div id="legend-tooltip" className={styles.legendTooltip}></div>
      </div>
    </div>
  );
};

export default MeetingHitRateChart;
