import React from "react";
import ReactEcharts from "echarts-for-react";
import styles from "../assets/styles/components/Heatmap.module.css";

export const Heatmap = props => {
  let { data=[], labels=[] } = props;
  data = data.map(function(item) {
    return [item[1], item[0], item[2] || "-"];
  });
  return (
    <ReactEcharts
      className={styles.heatmap}
      option={{
        tooltip: {
          position: "top"
        },
        animation: false,
        grid: {
          height: "50%",
          top: "10%"
        },
        xAxis: {
          type: "category",
          data: labels,
          splitArea: {
            show: true
          }
        },
        yAxis: {
          type: "category",
          data: labels,
          splitArea: {
            show: true
          }
        },
        visualMap: {
          min: 0,
          max: 10,
          calculable: true,
          orient: "horizontal",
          left: "center",
          bottom: "15%"
        },
        series: [
          {
            name: "Punch Card",
            type: "heatmap",
            data: data,
            label: {
              show: true
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      }}
    />
  );
};
