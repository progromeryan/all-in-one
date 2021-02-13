import React from "react";
import Enzyme, { shallow } from "enzyme";
import EnzymeAdapter from "@wojtekmaj/enzyme-adapter-react-17";
import App from "./App";

Enzyme.configure({adapter: new EnzymeAdapter()});

/**
 * Factory function to create a ShallowWrapper for the App component.
 * @function setup
 * @return {ShallowWrapper}
 */
const setup = () => shallow(<App/>);

const findByTestAttr = ( wrapper, val ) => wrapper.find(`[data-test='${val}']`);


test("renders without error", () => {
  const wrapper = setup();
  // 找到对应的element
  const appComponent = findByTestAttr(wrapper, 'component-app');
  expect(appComponent.length).toBe(1);
});

test("render button", () => {
  const wrapper = setup();
  const button = findByTestAttr(wrapper, 'increment-button');
  expect(button.length).toBe(1);
});

test("render counter display", () => {
  const wrapper = setup();
  const counterDisplay = findByTestAttr(wrapper, 'counter-display');
  expect(counterDisplay.length).toBe(1);
});

test("counter starts at 0", () => {
  const wrapper = setup();
  // text()永远返回string
  const count = findByTestAttr(wrapper, "count").text();
  expect(count).toBe("0");
});

test("click on the button increments counter display", () => {
  // find button
  const wrapper = setup();
  const button = findByTestAttr(wrapper, "increment-button");
  // click button
  button.simulate("click");
  // find the display and test if the number is right
  const count = findByTestAttr(wrapper, "count").text();
  expect(count).toBe("1");
});
