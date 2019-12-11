import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from "../../../components/BarChart";
import { Select } from "../../../components/Select";

export const Wind = props => {
  const [ value, setValue ] = useState("");

  const barWind = {
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
        label: "Avg Wind km/h",
        backgroundColor: "rgba(52, 73, 94,0.2)",
        borderColor: "rgba(52, 73, 94,1.0)",
        borderWidth: 1,
        hoverBackgroundColor: "rgba(52, 73, 94,0.4)",
        hoverBorderColor: "rgba(52, 73, 94,1.0)",
        data: [60, 45, 30, 22, 25, 26, 18, 23, 33, 45, 56, 64]
      }
    ]
  };

  function valueChange (e) {
    setValue(e.value);
  }


  return (
    <div className={styles.container}>
      <p id="wind">Wind</p>
      <span>Select year: </span><Select
        value={value}
        setValue={valueChange}
        options={["2010", "2011", "2012", "2013", "2014"]}
      />
      <BarChart data={barWind} />
    </div>
  );
};
