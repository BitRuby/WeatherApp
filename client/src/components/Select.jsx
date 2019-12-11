import React from 'react';
import styles from "../assets/styles/components/Select.module.css";
export const Select = (props) => {
    const {options, value, setValue} = props;
    return (
        <select className={styles.select} value={value} onChange={setValue}>
            {options.map((o,i) => (
                <option key={i} value={o}>{o}</option>
            ))}
        </select>
    );
}