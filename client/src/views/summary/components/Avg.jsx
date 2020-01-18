import React, { useEffect, useState } from "react";
import axios from "axios";
import { Select } from "../../../components/Select";
import { Line } from "../../../components/Line";
import styles from "../../../assets/styles/summary/Avg.module.css";

export const Avg = props => {
  const [data, setData] = useState([]);
  const [years] = useState(["2010", "2011", "2012", "2013", "2014"]);
  const [options] = useState([
    "pm25",
    "dewp",
    "TEMP",
    "PRES",
    "Iws",
    "Is",
    "Ir"
  ]);
  const { setIsLoading, isLoading } = props;
  const [year, setYear] = useState("2010");
  const [option, setOption] = useState("pm25");
  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      const r = await axios(
        `http://25.78.225.216:8086/beijing/avgByMonthAndYear?year=${year}&what=${option}`
      );
      setData({X: [...r.data.map(e => e.month)], Y: [...r.data.map(e => e[option])]});
      setIsLoading(false);
    };
    fetchData();
  }, [setIsLoading, option, year]);
  function yearChange(e) {
    setYear(e.target.value);
  }
  function optionChange(e) {
    setOption(e.target.value);
  }
  return (
    <div className={styles.container}>
      <p id="avg">Avg by Month/Year</p>
      <Select value={year} setValue={yearChange} options={years} />
      <Select value={option} setValue={optionChange} options={options} />
      {!isLoading && <Line dataX={data.X} dataY={data.Y} />}
    </div>
  );
};
