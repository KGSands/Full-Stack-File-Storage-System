import { useEffect, useState } from "react";
import CommonHeader from "./app/components/CommonHeader/CommonHeader";
import FilesTable from "./app/components/FilesTable/FilesTable";
import axios from "axios";
import FileUpload from "./app/components/FileUpload/FileUpload";
import { useTranslation } from "react-i18next";

const App = () => {
  const { t } = useTranslation();
  const [files, setFiles] = useState([]);
  const [isFileUploaded, setIsFileUploaded] = useState(false);
  const [isFileDeleted, setIsFileDeleted] = useState(false);
  const [isInitialMount, setIsInitialMount] = useState(true);

  useEffect(() => {
    if (isInitialMount || isFileDeleted || isFileUploaded) {
      axios.get("/files").then((success: any) => {
        if (success.data && success.data.length) {
          setFiles(success.data);
        } else {
          setFiles([]);
        }
        setIsFileUploaded(false);
        setIsFileDeleted(false);
        setIsInitialMount(false);
      });
    }
  }, [isFileDeleted, isFileUploaded, isInitialMount]);

  return (
    <div>
      <CommonHeader pageName={t("pages.main_title")}></CommonHeader>
      <FileUpload setIsFileUploaded={setIsFileUploaded}></FileUpload>
      <FilesTable
        files={files}
        setIsFileDeleted={setIsFileDeleted}
      ></FilesTable>
    </div>
  );
};

export default App;
