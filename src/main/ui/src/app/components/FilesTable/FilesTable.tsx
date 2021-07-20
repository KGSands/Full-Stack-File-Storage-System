import { useTranslation } from "react-i18next";
import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import styles from "./filesTable.module.scss";

type FilesTableProps = {
  files: any[];
  setIsFileDeleted: Dispatch<SetStateAction<boolean>>;
};

const FilesTable = ({ files, setIsFileDeleted }: FilesTableProps) => {
  const { t } = useTranslation();

  const deleteFile = (fileId: number) => {
    axios.delete(`/files/${fileId}`).then((success: any) => {
      setIsFileDeleted(true);
    });
  };

  return (
    <div className={styles.wrapper}>
      <h2>{t("files_table.title")}</h2>
      <div className={styles.container}>
        <div className={styles.table}>
          <div className={styles.titles}>
            <h3 className={styles.title}>{t("files_table.id")}</h3>
            <h3 className={styles.title}>{t("files_table.file_name")}</h3>
            <h3 className={styles.title}>{t("files_table.file_size")}</h3>
          </div>
          {files.length > 0 ?
            files.map((file) => (
              <div key={file.id} className={styles.files}>
                <span className={styles.fileProperty} title={file.id}>{file.id}</span>
                <span className={styles.fileProperty} title={file.fileName}>
                  {file.fileName}
                </span>
                <span className={styles.fileProperty}>
                  {file.fileSize / 1000}
                </span>
                <span title={""} className={styles.removeIcon}>
                    {
                      <img
                        src="/assets/remove.svg"
                        alt="remove"
                        height={15}
                        width={15}
                        onClick={() => deleteFile(file.id)}
                      />
                    }
                  </span>
              </div>
            )) : <div className={styles.noFiles}>{t('files_table.no_files')}</div> }
        </div>
      </div>
    </div>
  );
};

export default FilesTable;
