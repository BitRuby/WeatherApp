import React, { useState, useEffect } from "react";
import ReactEcharts from "echarts-for-react";
import ecStat from "echarts-stat";
import styles from "../assets/styles/components/LinearRegression.module.css";

export const LinearRegression = props => {
  let [myRegression, setRegression] = useState(ecStat.regression("linear", []));
  const {minX, minY} = props;
  useEffect(() => {
    var myRegression = ecStat.regression("linear", props.data);
    myRegression.points.sort(function(a, b) {
      return a[0] - b[0];
    });
    setRegression(myRegression);
  }, [props]);

  return (
    <ReactEcharts
      className={styles.linear_regression}
      option={{
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross"
          }
        },
        xAxis: {
          type: "value",
          min: minX,
          splitLine: {
            lineStyle: {
              type: "dashed"
            }
          }
        },
        yAxis: {
          type: "value",
          min: minY,
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
            data: props.data
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
