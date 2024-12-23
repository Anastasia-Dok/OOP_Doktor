package database.service;

import database.DTO.MathFunctionsDTO;
import database.DTO.PointDTO;
import database.entity.MathFunctionsEntity;
import database.entity.PointEntity;
import database.repositories.MathFunctionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MathFunctionsService {

    private final MathFunctionsRepository mathFunctionsRepository;

    @Autowired
    public MathFunctionsService(MathFunctionsRepository mathFunctionsRepository) {
        this.mathFunctionsRepository = mathFunctionsRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;
//Методы CRUD:
    public MathFunctionsDTO create(MathFunctionsDTO dto_obj){
        MathFunctionsEntity entity = convertToEntity(dto_obj);
        MathFunctionsEntity createdentity = mathFunctionsRepository.save(entity);

        return convertToDto(createdentity);
    }// Создает новую сущность на основе переданного DTO и сохраняет ее в базе данных.

    public MathFunctionsDTO createFromPoints(List<PointDTO> dto_obj){
        MathFunctionsEntity entity = convertToEntity(dto_obj);
        MathFunctionsEntity createdentity = mathFunctionsRepository.save(entity);

        return convertToDto(createdentity);
    }// Создает новую сущность на основе переданного DTO и сохраняет ее в базе данных.

    public MathFunctionsDTO read(Long id) {
        return mathFunctionsRepository.findById(id).map(this::convertToDto).orElse(null);
    }//Читает сущность из базы данных по ее идентификатору и возвращает DTO.

    public MathFunctionsDTO update(MathFunctionsDTO dto_obj){
        if(mathFunctionsRepository.existsById(dto_obj.getId())){
            MathFunctionsEntity entity = convertToEntity(dto_obj);
            MathFunctionsEntity updatedentity = mathFunctionsRepository.save(entity);

            return convertToDto(updatedentity);
        }
        throw new RuntimeException();
    }//Обновляет сущность, если она существует, и возвращает обновленный DTO

    public void delete(MathFunctionsDTO dto_obj){
        if(mathFunctionsRepository.existsById(dto_obj.getId())){
            mathFunctionsRepository.deleteById(dto_obj.getId());

        } else{
            throw new RuntimeException();
        }
    }//Удаляет сущность из базы данных по ее идентификатору.

    public MathFunctionsDTO getById(Long id){
        if(mathFunctionsRepository.existsById(id)){
            return convertToDto(mathFunctionsRepository.getById(id));
        }
        return null;
    }// Получает сущность по идентификатору, если она существует.


    private MathFunctionsDTO convertToDto(MathFunctionsEntity entity){
        MathFunctionsDTO dto_obj = new MathFunctionsDTO();

        dto_obj.setId(entity.getId());
        dto_obj.setFunctionName(entity.getFunctionName());
        dto_obj.setxTo(entity.getxTo());
        dto_obj.setxFrom(entity.getxFrom());
        dto_obj.setCount(entity.getCount());

        return dto_obj;
    }//Преобразует сущность в DTO, что позволяет передавать только необходимые данные в контроллеры.

    private MathFunctionsEntity convertToEntity(MathFunctionsDTO dto_obj){
        MathFunctionsEntity entity = new MathFunctionsEntity();

        if(dto_obj.getId() != null){
            entity.setId(dto_obj.getId());
        }
        entity.setFunctionName(dto_obj.getFunctionName());
        entity.setxTo(dto_obj.getxTo());
        entity.setxFrom(dto_obj.getxFrom());
        entity.setCount(dto_obj.getCount());

        return entity;
    }//Преобразует DTO в сущность, чтобы сохранить ее в базе данных.
    private MathFunctionsEntity convertToEntity(List<PointDTO> pointsDTO) {
        MathFunctionsEntity entity = new MathFunctionsEntity();

        // Преобразуем List<PointDTO> в List<PointEntity>
        List<PointEntity> points = pointsDTO.stream()
                .map(dto -> {
                    PointEntity pointEntity = new PointEntity();
                    pointEntity.setX(dto.getX());
                    pointEntity.setY(dto.getY());
                    // Дополнительно можно установить другие поля, если нужно
                    return pointEntity;
                })
                .collect(Collectors.toList());

        // Устанавливаем список точек в сущность
        entity.setPoints(points);

        // Проверяем, что список точек не пустой
        if (points != null && !points.isEmpty()) {
            // Находим максимальное значение x
            Double maxX = points.stream()
                    .map(PointEntity::getX)
                    .max(Double::compareTo)
                    .orElse(null);  // В случае пустого списка, возвращаем null

            // Находим минимальное значение x
            Double minX = points.stream()
                    .map(PointEntity::getX)
                    .min(Double::compareTo)
                    .orElse(null);  // В случае пустого списка, возвращаем null

            // Устанавливаем максимальное и минимальное значение x в сущность
            entity.setxTo(maxX);
            entity.setxFrom(minX);

            // Устанавливаем количество точек в сущность
            entity.setCount(points.size());
        } else {
            // Если список точек пустой, можно установить значения по умолчанию
            entity.setxTo(null);
            entity.setxFrom(null);
            entity.setCount(0);
        }

        return entity;
    }


    public List<MathFunctionsDTO> findsByName(String name) {
        return this.mathFunctionsRepository.findByFunctionName(name).stream().map(this::convertToDto).collect(Collectors.toList());
    }//Метод для поиска сущностей по имени функции. Он возвращает список DTO
}