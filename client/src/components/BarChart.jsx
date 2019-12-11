import React from "react";
import { Bar } from "react-chartjs-2";

export const BarChart = props => {
  const {data} = props;
  return (
    <Bar
      data={data}
      width={1000}
      height={600}
      options={{ maintainAspectRatio: true }}
    />
  );
};
