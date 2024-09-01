import React from "react";
import ReactDOM from "react-dom/client";
import { Route, Routes } from "react-router";
import { BrowserRouter } from "react-router-dom";

import PostContent from "./component/post/PostContent";
import PostIndex from "./component/post/PostIndex";
import { ThemeProvider } from "./hook/provider/ThemeProvider";
import PostPage from "./page/post/Post";
import reportWebVitals from "./reportWebVitals";
import MainPage from "@/page/main/Main";
import "./index.sass";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <ThemeProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/post" caseSensitive={false} element={<PostPage />}>
            <Route index element={<PostIndex />} />
            <Route path=":id" element={<PostContent />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
