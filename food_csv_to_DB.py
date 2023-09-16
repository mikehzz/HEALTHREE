# 오라클 DB
import oracledb
import pandas as pd
#import cx_Oracle


def main():
    #######################################
    # DB 접속
    #######################################

    # 각자 oracle db 정보
    #con = oracledb.connect(user='healthree', password='pcwk', dsn='192.168.0.123:1521/XE')
    con = oracledb.connect(user='###', password='###', dsn='###')

    print('con', con)

    # CSV 파일 읽기
    csv_file_path = 'food.csv'
    df = pd.read_csv(csv_file_path)

    # SQL 수행
    cursor = con.cursor()
    for index, row in df.iterrows():
        cursor.execute(
            'INSERT INTO FOOD (F_CODE, F_NAME, F_SIZE, F_CAL, F_CARBO, F_PROTEIN, F_FAT, F_SUGAR, F_NA, F_COLE) VALUES (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10)',
            (row['F_CODE'], row['F_NAME'], row['F_SIZE'], row['F_CAL'], row['F_CARBO'], row['F_PROTEIN'], row['F_FAT'],
             row['F_SUGAR'], row['F_NA'], row['F_COLE']))

    con.commit()
    cursor.close()
    con.close()


if __name__ == "__main__":
    main()