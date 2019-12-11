import React, { useState, useEffect } from 'react';
import styles from "../../../assets/styles/summary/Navigation.module.css";

export const Navigation = (props) => {


    return (
        <div className={styles.container}>
            <ul>
                <li><a href="#history">Historical data</a></li>
                <ul>
                    <li><a href="#temperature">Temperature</a></li>
                    <li><a href="#pressure">Pressure</a></li>
                    <li><a href="#humidity">Humidity</a></li>
                    <li><a href="#air">Air quality</a></li>
                    <li><a href="#precipitation">Precipitation</a></li>
                    <li><a href="#wind">Wind</a></li>
                </ul>
            </ul>
        </div>
    );
}