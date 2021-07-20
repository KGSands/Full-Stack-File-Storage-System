import i18n from "i18next";
import { initReactI18next } from "react-i18next";

import english from "./i18n/en-US.json";

const resources = { english: english };

i18n
  .use(initReactI18next) // passes i18n down to react-i18next
  .init({
    resources,
    lng: "english", // Could dynamically load based on user's locale

    interpolation: {
      escapeValue: false, // react already safes from xss
    },
  });

export default i18n;
