import React, { useState, useEffect } from 'react';
import styles from "../../../assets/styles/summary/Navigation.module.css";

export const Navigation = (props) => {


    const {changeCategory, category} = props;
    const navigation=['Matrix', 'Regression', 'Box', 'Temperature', 'Pressure', 'Humidity', 'Air Quality', 'Precipitation', 'Wind'];

    function renderNavigation() {
        return navigation.map((e, i) => (
            <li key={i} className={(category===e)? styles.active : ""} onClick={() => changeCategory(e)}>{e}</li>
        ))
    }

    return (
        <div className={styles.container}>
            <ul>
                <li><a href="#history">Historical data charts</a></li>
                <ul>
                    {renderNavigation()}
                </ul>
            </ul>
        </div>
    );
}