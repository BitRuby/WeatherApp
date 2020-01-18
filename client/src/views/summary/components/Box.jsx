import React, { useEffect, useState } from "react";
import { BoxPlot } from "../../../components/BoxPlot";
import styles from "../../../assets/styles/summary/Box.module.css";
import axios from "axios";
import { Select } from "../../../components/Select";

export const Box = props => {
  const [data, setData] = useState([]);
  const [year, setYear] = useState("2010");
  const [col, setCol] = useState("pm25");
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
        `${process.env.REACT_APP_API_URL}/beijing/boxes?col=${col}&year=${year}`
      );
      setData(r.data);
      setIsLoading(false);
    };
    fetchData();
  }, [setIsLoading, setData, col, year]);
  function yearChange(e) {
    setYear(e.target.value);
  }
  function colChange(e) {
    setCol(e.target.value);
  }
  return (
    <div className={styles.container}>
      <p id="box">Box Plot</p>
      <Select value={year} setValue={yearChange} options={years} />
      <Select value={col} setValue={colChange} options={options} />
      {!isLoading && <BoxPlot data={data} label={col} />}
    </div>
  );
};
