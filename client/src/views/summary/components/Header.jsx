import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBars, faArrowLeft } from '@fortawesome/free-solid-svg-icons'
import styles from "../../../assets/styles/summary/Header.module.css";
export const Header = (props) => {

	const { title, setNav, nav } = props;

	const toggleMenu = () => {
		setNav(!nav);
	}
	return (
		<div className={styles.container}>
			<h4 className={styles.title}><FontAwesomeIcon icon={faArrowLeft} /> <span className={styles.titleText}>Summary - {title}</span></h4>
			<FontAwesomeIcon icon={faBars} className={styles.bars} onClick={toggleMenu} />
		</div>
	);
}