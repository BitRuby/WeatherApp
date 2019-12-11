import React, { Fragment, useState, useEffect } from "react";
import styles from "../../assets/styles/summary/Summary.module.css";
import { Header } from "./components/Header";
import { Navigation } from "./components/Navigation";
import { Temperature } from "./components/Temperature";
export const Summary = props => {
  const [nav, setNav] = useState(true);
  const [title, setTitle] = useState("Shanghai - People's Republic of China - 31°12′N 121°30′E");
  return (
    <Fragment>
      <Header title={title} setNav={setNav} nav={nav}></Header>
      <div className={styles.container}>
        {nav && <Navigation />}
        <Temperature />
      </div>
    </Fragment>
  );
};
