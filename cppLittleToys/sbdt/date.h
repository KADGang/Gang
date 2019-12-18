#pragma once
#include <iostream>

using namespace std;

class date {
private:
	int year;
	int month;
	int day;
	static int DATE[];
public:
	date(int YEAR, int MONTH, int DAY);
	date();
	friend ostream& operator<<(ostream& output, const date& date);
	friend istream& operator>>(istream& input, date& date);
	bool leap_year(int year);//�ж�����
	bool valid();//�ж������Ƿ���Ч
	date& operator = (const date& date);
	date operator + (int Day);//Day��������
	int operator - (date& date);//�������������
	date operator - (int Day);//Day��ǰ������
	bool operator > (date& date);
	bool operator < (date& date);
	bool operator == (date& date);//�ж������Ƿ���ͬ
};

