import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from "../../../components/BarChart";
import { Select } from "../../../components/Select";

export const Precipitation = props => {
  const [value, setValue] = useState("");

  const barPrecipitation = {
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
        label: "Avg Precipitation mm2",
        backgroundColor: "rgba(52, 152, 219,0.2)",
        borderColor: "rgba(52, 152, 219,1.0)",
        borderWidth: 1,
        hoverBackgroundColor: "rgba(52, 152, 219,0.4)",
        hoverBorderColor: "rgba(52, 152, 219,1.0)",
        data: [155, 122, 99, 86, 75, 60, 55, 60, 80, 110, 157, 189]
      }
    ]
  };
  function valueChange(e) {
    setValue(e.value);
  }
  return (
    <div className={styles.container}>
      <p id="precipitation">Precipitation</p>
      <span>Select year: </span><Select
        value={value}
        setValue={valueChange}
        options={["2010", "2011", "2012", "2013", "2014"]}
      />
      <BarChart data={barPrecipitation} />
    </div>
  );
};
