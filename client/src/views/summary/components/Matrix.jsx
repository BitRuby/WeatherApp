import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import axios from "axios";
import { Heatmap } from "../../../components/Heatmap";


export const Matrix = props => {
  const [data, setData] = useState({});
  const [isError, setIsError] = useState(false);
  const [url, setUrl] = useState(
    "http://localhost:8086/beijing/correlationMatrix"
  );
  useEffect(() => {
    const {setIsLoading} = props;
    const fetchData = async () => {
      setIsLoading(true);
      setIsError(false);
      const r = await axios(url);
      setData(r.data);
      setIsLoading(false);
    };
    fetchData();
  }, [url]);
  const {isLoading} = props;
  return (
    <div className={styles.container}>
      <p id="temperature">Summary Matrix</p>
      {isError && <p>Error: Exception</p>}
      {!isLoading && <Heatmap data={data.matrix} labels={data.labels} />}
    </div>
  );
};
