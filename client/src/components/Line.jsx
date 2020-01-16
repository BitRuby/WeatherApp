import React from "react";
import ReactEcharts from "echarts-for-react";

export const Line = props => {
  const { dataX, dataY, name } = props;
  return (
    <ReactEcharts
      option={{
        color: ["#3398DB"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        xAxis: [
          {
            type: "category",
            data: dataX,
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: "value"
          }
        ],
        series: [
          {
            name: name,
            type: "bar",
            barWidth: "60%",
            data: dataY
          }
        ]
      }}
    />
  );
};