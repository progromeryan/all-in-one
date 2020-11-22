import React, { Fragment, useState } from 'react';
import { useCustomHookApi } from "../api/useCustomHookApi";

function App() {
  const [query, setQuery] = useState('redux');
  const [{data, isLoading, isError}, doFetch] = useCustomHookApi(
    'https://hn.algolia.com/api/v1/search?query=redux',
    {hits: []},
  );

  return (
    <Fragment>
      <form onSubmit={event => {
        doFetch(`http://hn.algolia.com/api/v1/search?query=${query}`);
        event.preventDefault();
      }}>
        <input
          type="text"
          value={query}
          onChange={event => setQuery(event.target.value)}
        />
        <button type="submit">Search</button>
        {isError && <div>Something went wrong ...</div>}
        {isLoading ? (
          <div>Loading ...</div>
        ) : (
          <ul>
            {data.hits.map(item => (
              <li key={item.objectID}>
                <a href={item.url}>{item.title}</a>
              </li>
            ))}
          </ul>)}
      </form>
    </Fragment>
  );
}
