import React from "react";
import ReactEcharts from "echarts-for-react";
import styles from "../assets/styles/components/BoxPlot.module.css";
import echarts from "echarts";
import prepareBoxplotData from "../../node_modules/echarts/extension/dataTool/prepareBoxplotData";



export const BoxPlot = props => {
  const {data} = props;
  var d = prepareBoxplotData(data);
  return (
    <ReactEcharts
      className={styles.boxplot}
      option={{
        title: [
          {
            text: "Michelson-Morley Experiment",
            left: "center"
          }
        ],
        tooltip: {
          trigger: "item",
          axisPointer: {
            type: "shadow"
          }
        },
        grid: {
          left: "10%",
          right: "10%",
          bottom: "15%"
        },
        xAxis: {
          type: "category",
          data: d.axisData,
          boundaryGap: true,
          nameGap: 30,
          splitArea: {
            show: false
          },
          axisLabel: {
            formatter: "expr {value}"
          },
          splitLine: {
            show: false
          }
        },
        yAxis: {
          type: "value",
          name: "km/s minus 299,000",
          splitArea: {
            show: true
          }
        },
        series: [
          {
            name: "boxplot",
            type: "boxplot",
            data: d.boxData,
            tooltip: {
              formatter: function(param) {
                return [
                  "Experiment " + param.name + ": ",
                  "upper: " + param.data[5],
                  "Q3: " + param.data[4],
                  "median: " + param.data[3],
                  "Q1: " + param.data[2],
                  "lower: " + param.data[1]
                ].join("<br/>");
              }
            }
          },
          {
            name: "outlier",
            type: "scatter",
            data: d.outliers
          }
        ]
      }}
    />
  );
};