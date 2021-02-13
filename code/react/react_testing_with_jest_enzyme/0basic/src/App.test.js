import React from "react";
import Enzyme, { shallow } from "enzyme";
import EnzymeAdapter from "@wojtekmaj/enzyme-adapter-react-17";
import App from "./App";

Enzyme.configure({ adapter: new EnzymeAdapter() });

test("renders without error", () => {
  const wrapper = shallow(<App />);
  // 找到对应的element
  const appComponent = wrapper.find("[data-test='component-app']");
  expect(appComponent.length).toBe(1);
});

test("render button", () => {});

test("render counter display", () => {});

test("counter starts at 0", () => {});

test("click on the button increments counter display", () => {});
