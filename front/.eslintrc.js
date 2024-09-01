module.exports = {
  env: {
    browser: true,
    node: true,
    es2021: true
  },
  parser: "@typescript-eslint/parser",
  extends: [
    "eslint:recommended",
    "plugin:react/recommended",
    "plugin:@typescript-eslint/recommended",
    "plugin:prettier/recommended"
  ],
  plugins: ["prettier", "import"],
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: 2020,
    sourceType: "module"
  },
  rules: {
    "prettier/prettier": "error",
    "no-unused-vars": "off", // TypeScript가 다루므로 비활성화
    "@typescript-eslint/no-unused-vars": "off",
    "@typescript-eslint/no-empty-interface": "off",
    "react/react-in-jsx-scope": "off", // React 17 이상에서는 필요 없음
    "comma-dangle": 0,
    "import/order": [
      "error",
      {
        groups: [["builtin", "external", "internal"]],
        pathGroups: [
          {
            pattern: "react",
            group: "external",
            position: "before"
          }
        ],
        alphabetize: {
          order: "asc",
          caseInsensitive: true
        },
        "newlines-between": "always"
      }
    ]
  },
  settings: {
    react: {
      version: "detect" // React 버전을 자동으로 감지
    }
  }
};
