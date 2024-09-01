import { Link } from "react-router-dom";

import "./Header.sass";

import FlexBox from "./FlexBox";
import { useTheme } from "@/hook/provider/ThemeProvider";

interface HeaderProps {}

const headerProps: HeaderProps = {};

export default function Header(props: HeaderProps): JSX.Element {
  const { isDarkTheme, toggleTheme } = useTheme();

  return (
    <FlexBox className="header">
      <p className="logo">LOGO</p>
      <FlexBox>
        <Link to={"/"}>Home</Link>
        <Link to={"/post"}>Post</Link>
      </FlexBox>
      <button onClick={toggleTheme}></button>
    </FlexBox>
  );
}
