import React, { useState, useEffect } from "react";
import styles from "../../../assets/styles/summary/Matrix.module.css";
import axios from "axios";
import { Heatmap } from "../../../components/Heatmap";

export const Matrix = props => {
  const [data, setData] = useState({});
  const [url] = useState(
    `${process.env.REACT_APP_API_URL}/beijing/correlationMatrix`
  );
  const { setIsLoading, isLoading } = props;
  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      const r = await axios(url);
      setData(r.data);
      setIsLoading(false);
    };
    fetchData();
  }, [setIsLoading, url]);

  return (
    <div className={styles.container}>
      <p id="summary">Summary Matrix</p>
      {!isLoading && <Heatmap data={data.matrix} labels={data.labels} />}
    </div>
  );
};
