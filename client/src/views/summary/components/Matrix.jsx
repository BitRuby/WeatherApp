import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Temperature.module.css";
import axios from "axios";
import { Heatmap } from "../../../components/Heatmap";

export const Matrix = props => {
  const [data, setData] = useState({});
  const [url] = useState(
    "http://25.78.225.216:8086/beijing/correlationMatrix"
  );
  useEffect(() => {
    const { setIsLoading } = props;
    const fetchData = async () => {
      setIsLoading(true);
      const r = await axios(url);
      setData(r.data);
      setIsLoading(false);
    };
    fetchData();
  }, []);
  const { isLoading } = props;
  return (
    <div className={styles.container}>
      <p id="summary">Summary Matrix</p>
      {!isLoading && <Heatmap data={data.matrix} labels={data.labels} />}
    </div>
  );
};
