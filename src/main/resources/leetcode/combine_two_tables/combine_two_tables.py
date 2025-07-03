import pandas as pd


def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    return pd.merge(person.reindex(['personId', 'firstName', 'lastName'], axis=1),
                    address.drop('addressId', axis=1),
                    how='left',
                    on='personId').drop('personId', axis=1)


def combine_two_tables1(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    table = []

    for idx in person.index:
        row = []

        row.append(person.at[idx, 'firstName'])
        row.append(person.at[idx, 'lastName'])

        city = None
        state = None
        for a_idx in address.index:
            if address.at[a_idx, 'personId'] == person.at[idx, 'personId']:
                city = address.at[a_idx, 'city']
                state = address.at[a_idx, 'state']
                break
        row.append(city)
        row.append(state)

        table.append(row)

    return pd.DataFrame(data=table, columns=['firstName', 'lastName', 'city', 'state'])


person = pd.read_csv('person.csv')
address = pd.read_csv('address.csv')
print(combine_two_tables(person, address))
