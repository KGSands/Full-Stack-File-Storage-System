import { Dispatch, SetStateAction, useState } from "react";
import axios from "axios";
import { useTranslation } from "react-i18next";
import styles from "./fileUpload.module.scss"

type FileUploadProps = {
  setIsFileUploaded: Dispatch<SetStateAction<boolean>>;
};

const FileUpload = ({ setIsFileUploaded }: FileUploadProps) => {
  const { t } = useTranslation();
  const [selectedFile, setSelectedFile] = useState<File>(null);
  const [showError, setShowError] = useState<boolean>(false);

  const handleSubmission = () => {
    if (selectedFile !== null) {
      var formData = new FormData();
      formData.append("file", selectedFile);
      axios
        .post("/files", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((success: any) => {
          setIsFileUploaded(true);
        })
        .catch((error: any) => {
          setShowError(true);
        });
    }
  };

  const changeHandler = (event: any) => {
    if (event && event.target && event.target.files && event.target.files[0]) {
      setSelectedFile(event.target.files[0]);
      setShowError(false);
    }
  };

  return (
    <div>
      <h2>{t("upload_files.title")}</h2>
      <input type="file" name="file" onChange={changeHandler} />
      <button onClick={handleSubmission}>{t('submit')}</button>
      {showError && <div className={styles.error}>{t('upload_files.error')}</div>}
    </div>
  );
};

export default FileUpload;
