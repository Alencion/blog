import { Outlet } from "react-router-dom";

import FlexBox from "@/component/common/FlexBox";
import Header from "@/component/common/Header";
import SideBar from "@/component/common/SideBar";

interface PostPageProps {}

export default function PostPage(props: PostPageProps): JSX.Element {
  return (
    <FlexBox direction="column">
      <Header />
      <FlexBox>
        <SideBar />
        <Outlet />
      </FlexBox>
    </FlexBox>
  );
}
