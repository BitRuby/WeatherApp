import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from "../../../components/BarChart";
import { Select } from "../../../components/Select";

export const Humidity = props => {
  const [value, setValue] = useState("");

  const barHumidity = {
    labels: [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December"
    ],
    datasets: [
      {
        label: "Avg Humidity",
        backgroundColor: "rgba(26, 188, 156,0.2)",
        borderColor: "rgba(22, 160, 133,1.0)",
        borderWidth: 1,
        hoverBackgroundColor: "rgba(26, 188, 156,0.4)",
        hoverBorderColor: "rgba(22, 160, 133,1.0)",
        data: [83, 82, 81, 79, 77, 74, 56, 51, 52, 66, 81, 83]
      }
    ]
  };
  function valueChange(e) {
    setValue(e.value);
  }
  return (
    <div className={styles.container}>
      <p id="humidity">Humidity</p>
      <span>Select year: </span><Select
        value={value}
        setValue={valueChange}
        options={["2010", "2011", "2012", "2013", "2014"]}
      />
      <BarChart data={barHumidity} />
    </div>
  );
};
