import React, { Fragment, useState } from "react";
import styles from "../../assets/styles/summary/Summary.module.css";
import { Header } from "./components/Header";
import { Navigation } from "./components/Navigation";
import { Regression } from "./components/Regression";
import { Matrix } from "./components/Matrix";
import { Avg } from "./components/Avg";
import { Box } from "./components/Box";
import { Sum } from "./components/Sum";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleNotch } from "@fortawesome/free-solid-svg-icons";
export const Summary = props => {
  const [nav, setNav] = useState(true);
  const [category, setCategory] = useState("Regression");
  const [isLoading, setIsLoading] = useState(false);
  const [title] = useState(
    "Beijing - People's Republic of China - 39°55′N 116°23′E"
  );
  return (
    <Fragment>
      {isLoading && (
        <div className={styles.spinnerContainer}>
          <FontAwesomeIcon
            icon={faCircleNotch}
            spin
            style={{ width: "50px", height: "50px", zIndex: "999" }}
            className={styles.spinner}
          />
        </div>
      )}
      <Header title={title} setNav={setNav} nav={nav}></Header>
      <div className={styles.container}>
        {nav && <Navigation changeCategory={setCategory} category={category} />}
        <div className={styles.content}>
          {category === "Matrix" && (
            <Matrix isLoading={isLoading} setIsLoading={setIsLoading} />
          )}
          {category === "Regression" && (
            <Regression isLoading={isLoading} setIsLoading={setIsLoading} />
          )}
          {category === "Box" && (
            <Box isLoading={isLoading} setIsLoading={setIsLoading} />
          )}
          {category === "Avg by Month/Year" && (
            <Avg isLoading={isLoading} setIsLoading={setIsLoading} />
          )}
          {category === "Sum" && (
            <Sum isLoading={isLoading} setIsLoading={setIsLoading} />
          )}
        </div>
      </div>
    </Fragment>
  );
};
