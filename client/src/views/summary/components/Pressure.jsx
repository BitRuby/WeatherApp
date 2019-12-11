import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from "../../../components/BarChart";
import { Select } from "../../../components/Select";

export const Pressure = props => {
  const [value, setValue] = useState("");

  const barPressure = {
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
        label: "Avg Pressure",
        backgroundColor: "rgba(46, 204, 113,0.2)",
        borderColor: "rgba(39, 174, 96,1.0)",
        borderWidth: 1,
        hoverBackgroundColor: "rgba(46, 204, 113,0.4)",
        hoverBorderColor: "rgba(39, 174, 96,1.0)",
        data: [
          995,
          993,
          989,
          1000,
          999,
          1004,
          1009,
          1013,
          1015,
          1005,
          1001,
          999
        ]
      }
    ]
  };
  function valueChange(e) {
    setValue(e.value);
  }

  return (
    <div className={styles.container}>
      <p id="pressure">Pressure</p>
      <span>Select year: </span><Select
        value={value}
        setValue={valueChange}
        options={["2010", "2011", "2012", "2013", "2014"]}
      />
      <BarChart data={barPressure} />
    </div>
  );
};
