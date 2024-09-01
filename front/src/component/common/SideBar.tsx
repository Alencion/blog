import FlexBox from "./FlexBox";

interface SideBarProps {}

export default function SideBar(props: SideBarProps) {
  return (
    <FlexBox direction="column">
      <ul>
        <li>1</li>
        <li>2</li>
        <li>3</li>
      </ul>
    </FlexBox>
  );
}
