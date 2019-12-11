import React, { Fragment, useState, useEffect } from "react";
import styles from "../../assets/styles/summary/Summary.module.css";
import { Header } from "./components/Header";
import { Navigation } from "./components/Navigation";
import { Temperature } from "./components/Temperature";
import { Pressure } from "./components/Pressure";
import { AirQuality } from "./components/AirQuality";
import { Humidity } from "./components/Humidity";
import { Precipitation } from "./components/Precipitation";
import { Wind } from "./components/Wind";
export const Summary = props => {
  const [nav, setNav] = useState(true);
  const [category, setCategory] = useState("Temperature");
  const [title, setTitle] = useState(
    "Shanghai - People's Republic of China - 31°12′N 121°30′E"
  );
  return (
    <Fragment>
      <Header title={title} setNav={setNav} nav={nav}></Header>
      <div className={styles.container}>
        {nav && <Navigation changeCategory={setCategory} category={category} />}
        <div className={styles.content}>
          {category === "Temperature" && <Temperature />}
		  {category === "Pressure" && <Pressure />}
          {category === "Humidity" && <Humidity />}
          {category === "Air Quality" && <AirQuality />}
          {category === "Precipitation" && <Precipitation />}
          {category === "Wind" && <Wind />}
        </div>
      </div>
    </Fragment>
  );
};
