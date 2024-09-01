import styled from "styled-components";

interface FlexBoxProps {
  className?: string;
  direction?: "row" | "row-reverse" | "column" | "column-reverse";
  wrap?: "nowrap" | "wrap" | "wrap-reverse";
  width?: "auto" | string;
}

export default function FlexBox({
  children,
  className,
  direction = "row",
  wrap = "wrap",
  width = "auto"
}: React.PropsWithChildren<FlexBoxProps>): JSX.Element {
  return (
    <FlexDiv
      direction={direction}
      wrap={wrap}
      width={width}
      className={className}
    >
      {children}
    </FlexDiv>
  );
}

const FlexDiv = styled.div<FlexBoxProps>`
  display: flex;
  flex-direction: ${(props) => props.direction};
  flex-wrap: ${(props) => props.wrap};
  width: ${(props) => props.width};
`;
