import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from "../../../components/BarChart";
import { Select } from "../../../components/Select";

export const AirQuality = props => {
  const [value, setValue] = useState("");

  const barAirQuality = {
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
        label: "Avg Air Pollution PM10",
        backgroundColor: "rgba(127, 140, 141,0.2)",
        borderColor: "rgba(127, 140, 141,1.0)",
        borderWidth: 1,
        hoverBackgroundColor: "rgba(127, 140, 141,0.4)",
        hoverBorderColor: "rgba(127, 140, 141,1.0)",
        data: [1000, 886, 651, 300, 220, 201, 199, 186, 174, 205, 505, 810]
      }
    ]
  };
  function valueChange(e) {
    setValue(e.value);
  }
  return (
    <div className={styles.container}>
      <p id="air">Air quality</p>
      <span>Select year: </span><Select
        value={value}
        setValue={valueChange}
        options={["2010", "2011", "2012", "2013", "2014"]}
      />
      <BarChart data={barAirQuality} />
    </div>
  );
};
