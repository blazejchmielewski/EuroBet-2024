export interface Authority {
    authority: string;
}

export interface User{
    id: number;
    uuid: string;
    firstname: string;
    lastname: string;
    email: string;
    password: string;
    department: string;
    role: string[];
    enabled: boolean;
    authorities: Authority[];
    username: string;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    accountNonExpired: boolean;
}

export interface RankingData{
    firstname: string;
    lastname: string;
    totalPoints: number;
    rank: number;
}

export interface userAcountStatus{
    status : number;
}

export interface PaymentHistory{
    firstname: string;
    lastname: string;
    payment: number;
    timestamp: Date;
}