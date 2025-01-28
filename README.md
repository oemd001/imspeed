# imspeed

```
const aggregatedData = visibleMeetingTypes.reduce(
  (acc, meetingType) => {
    const data = hitRatiosByMeetingType[meetingType];
    if (data) {
      acc.totalAttendees += data.totalAttendees ?? 0;
      acc.hitAttendees += data.hitAttendees ?? 0;
    }
    return acc;
  },
  { totalAttendees: 0, hitAttendees: 0 }
);

const combinedHitRatio =
  aggregatedData.totalAttendees > 0
    ? (aggregatedData.hitAttendees / aggregatedData.totalAttendees) * 100
    : 0;

const yValues = [{ hitRatio: combinedHitRatio }];

// column chart val
<ColumnChart
  xAxisLabels={["Overall Hit Rate"]}
  suppressDataLabels
  series={[
    {
      data: [combinedHitRatio],
    },
  ]}
  yFormatter={yFormatter}
  className={`${styles.chart} ${styles.moveUp}`}
  shadowHeight={100}
  type="bar"
  stacking="normal"
/>
```
