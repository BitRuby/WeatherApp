import React, { useState, useEffect } from 'react';
import styles from "../../../assets/styles/summary/Temperature.module.css";
import { BarChart } from '../../../components/BarChart';

export const Temperature = (props) => {

    const barTemperature = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        datasets: [
          {
            label: 'Avg Temeperature',
            backgroundColor: 'rgba(255,99,132,0.2)',
            borderColor: 'rgba(255,99,132,1)',
            borderWidth: 1,
            hoverBackgroundColor: 'rgba(255,99,132,0.4)',
            hoverBorderColor: 'rgba(255,99,132,1)',
            data: [2,1,9,13,19,25,28,28,23,18,12,5]
          }
        ]
    }

    return (
        <div className={styles.container}>
            <h5 id="temperature">Temperature</h5>
            <BarChart data={barTemperature}/>
        </div>
    );
}