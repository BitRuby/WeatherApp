import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Regression.module.css";
import { LinearRegression } from "../../../components/LinearRegression";
import { Select } from "../../../components/Select";
import axios from "axios";

export const Regression = props => {
  const [data, setData] = useState([]);
  const [minX, setMinX] = useState(0);
  const [minY, setMinY] = useState(0);
  const [year, setYear] = useState("2010");
  const [col1, setCol1] = useState("month");
  const [col2, setCol2] = useState("TEMP");
  const { setIsLoading, isLoading } = props;
  const [options] = useState([
    "year",
    "month",
    "day",
    "hour",
    "pm25",
    "dewp",
    "TEMP",
    "PRES",
    "Iws",
    "Is",
    "Ir"
  ]);
  const [years] = useState(["2010", "2011", "2012", "2013", "2014"]);
  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      const r = await axios(
        `${process.env.REACT_APP_API_URL}/beijing/regression?col1=${col1}&col2=${col2}&year=${year}`
      );
      setData(r.data);
      setMinX(Math.min(...r.data.map(e => { return e[0]})));
      setMinY(Math.min(...r.data.map(e => { return e[1]})));
      setIsLoading(false);
    };
    fetchData();
  }, [setIsLoading, year, col1, col2]);
  function yearChange(e) {
    setYear(e.target.value);
  }
  function col1Change(e) {
    setCol1(e.target.value);
  }
  function col2Change(e) {
    setCol2(e.target.value);
  }
  return (
    <div className={styles.container}>
      <p id="regression">Regression</p>
      <Select value={year} setValue={yearChange} options={years} />
      <Select value={col1} setValue={col1Change} options={options} />
      <Select value={col2} setValue={col2Change} options={options} />
      {!isLoading && <LinearRegression data={data} minX={minX} minY={minY} />}
    </div>
  );
};
