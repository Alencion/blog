import { useParams } from "react-router-dom";

import FlexBox from "../common/FlexBox";

interface PostContentProps {}

export default function PostContent(props: PostContentProps): JSX.Element {
  const { id } = useParams();

  return <FlexBox>{id}</FlexBox>;
}
