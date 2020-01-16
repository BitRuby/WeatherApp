import React from "react";
import ReactEcharts from "echarts-for-react";
import ecStat from "echarts-stat";
import styles from "../assets/styles/components/LinearRegression.module.css";

export const LinearRegression = props => {
  const { data, title, subtitle } = props;
  var myRegression = ecStat.regression("linear", data);

  myRegression.points.sort(function(a, b) {
    return a[0] - b[0];
  });
  return (
    <ReactEcharts
      className={styles.linear_regression}
      option={{
        title: {
          text: title,
          subtext: subtitle,
          left: "center"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross"
          }
        },
        xAxis: {
          type: "value",
          splitLine: {
            lineStyle: {
              type: "dashed"
            }
          }
        },
        yAxis: {
          type: "value",
          min: 1.5,
          splitLine: {
            lineStyle: {
              type: "dashed"
            }
          }
        },
        series: [
          {
            name: "scatter",
            type: "scatter",
            emphasis: {
              label: {
                show: true,
                position: "left",
                color: "blue",
                fontSize: 16
              }
            },
            data: data
          },
          {
            name: "line",
            type: "line",
            showSymbol: false,
            data: myRegression.points,
            markPoint: {
              itemStyle: {
                color: "transparent"
              },
              label: {
                show: true,
                position: "left",
                formatter: myRegression.expression,
                color: "#333",
                fontSize: 14
              },
              data: [
                {
                  coord: myRegression.points[myRegression.points.length - 1]
                }
              ]
            }
          }
        ]
      }}
    />
  );
};
