import React, { useState, useEffect, useReducer } from 'react'
import axios from 'axios';

const dataFetchReducer = ( state, action ) => {
  switch (action.type) {
    case 'FETCH_INIT':
      return {
        ...state,
        isLoading: true,
        isError: false,
      };
    case 'FETCH_SUCCESS':
      return {
        ...state,
        isLoading: false,
        isError: false,
        data: action.payload,
      };
    case 'FETCH_FAILURE':
      return {
        ...state,
        isLoading: false,
        isError: true,
      };
    default:
      throw new Error();
  }
}

export const useReducerApi = ( initialUrl, initialData ) => {
  const [url, setUrl] = useState(initialUrl);
  const [state, dispatch] = useReducer(dataFetchReducer, {
    isLoading: false,
    isError: false,
    data: initialData,
  });

  useEffect(() => {
    /**
     * It's a common problem in React that component state
     * is set even though the component got already unmounted.
     * how to prevent setting state for unmounted components in custom hook.
     *
     * Every Effect Hook comes with a clean up function
     * which runs when a component unmounts.
     * The clean up function is the one function returned from the hook.
     * In our case, we use a boolean flag called didCancel to
     * let our data fetching logic know about the state (mounted/unmounted) of the component.
     * If the component did unmount,
     * the flag should be set to true which results in preventing to
     * set the component state after the data fetching has been asynchronously resolved eventually.
     */
    let didCancel = false;
    const fetchData = async () => {
      dispatch({type: 'FETCH_INIT'});

      try {
        const result = await axios(url);

        if (!didCancel) {
          dispatch({type: 'FETCH_SUCCESS', payload: result.data});
        }
      } catch (error) {
        if (!didCancel) {
          dispatch({type: 'FETCH_FAILURE'});
        }
      }
    };

    fetchData();
    return () => {
      didCancel = true;
    };
  }, [url]);

  return [state, setUrl];
}
