package com.hhlt.konsultado.controller;


import com.hhlt.konsultado.entity.Dict;
import com.hhlt.konsultado.mapper.DictDao;
import com.hhlt.konsultado.table.PageTableHandler;
import com.hhlt.konsultado.table.PageTableHandler.CountHandler;
import com.hhlt.konsultado.table.PageTableHandler.ListHandler;
import com.hhlt.konsultado.table.PageTableRequest;
import com.hhlt.konsultado.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictDao dictDao;

	@PreAuthorize("hasAuthority('dict:add')")
	@PostMapping
	public Dict save(@RequestBody Dict dict) {
		Dict d = dictDao.getByTypeAndK(dict.getType(), dict.getK());
		if (d != null) {
			throw new IllegalArgumentException("类型和key已存在");
		}
		dictDao.save(dict);

		return dict;
	}

	@GetMapping("/{id}")
	public Dict get(@PathVariable Long id) {
		return dictDao.getById(id);
	}

	@PreAuthorize("hasAuthority('dict:add')")
	@PutMapping
	public Dict update(@RequestBody Dict dict) {
		dictDao.update(dict);

		return dict;
	}

	@PreAuthorize("hasAuthority('dict:query')")
	@GetMapping(params = { "start", "length" })
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return dictDao.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<Dict> list(PageTableRequest request) {
				return dictDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@PreAuthorize("hasAuthority('dict:del')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		dictDao.delete(id);
	}

	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		return dictDao.listByType(type);
	}
}
